const catchAsync = require('../utils/catchAsync');
const { productService, userService } = require('../services');

const userController = {
    getProfile: catchAsync(async (req, res) => {
        const products = await productService.get({}).catch((err) => {
            return res.status(400).send('Get data fail');
        });
        res.status(200).json(products);
    }),

    getProducts: catchAsync(async (req, res) => {
        const { store: products } = await userService
            .getOne(
                { _id: req.params.userId },
                { field: 'store -_id', populate: 'store.product' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail' + err);
            });
        const result = products.map((product) => {
            return product.product;
        });
        res.status(200).json(result);
    }),

    getProductDetail: catchAsync(async (req, res) => {
        const product = await productService
            .getOne({ _id: req.params.id })
            .catch((err) => {
                return res.status(400).send('Get data fail');
            });
        res.status(200).json(product);
    }),

    createProduct: catchAsync(async (req, res) => {
        console.log(req.body, req.file);
        const image = req.file ? '/uploads/product/' + req.file.filename : '';

        const product = await productService
            .create({ ...req.body, image })
            .catch((err) => {
                return res.status(400).send('Create product fail' + err);
            });

        const store = {
            product: product._id,
            status: 'pending',
        };

        await userService
            .update({ _id: req.params.userId }, { $push: { store } })
            .catch((err) => {
                return res.status(400).send('Add product to user fail');
            });
        res.status(200).send();
    }),

    updateProduct: catchAsync(async (req, res) => {
        const { imageInp } = req.body;
        let { image } = await featureService.getOne(
            { _id: req.params.id },
            'image'
        );

        if (req.file) {
            if (image) {
                fs.unlink(`public/${image}`, (err) => {
                    if (err) {
                        req.flash('error', 'Cập nhật tính năng thất bại');
                        return res.redirect('back');
                    }
                });
            }
            image = '/uploads/feature/' + req.file.filename;
        } else if (!imageInp && image) {
            fs.unlink(`public/${image}`, (err) => {
                if (err) {
                    req.flash('error', 'Cập nhật tính năng thất bại');
                    return res.redirect('back');
                }
            });
            image = '';
        }

        await featureService
            .update({ _id: req.params.id }, { ...req.body, image })
            .catch((err) => {
                req.flash('error', 'Cập nhật tính năng thất bại');
                return res.redirect('back');
            });

        req.flash('success', 'Cập nhật tính năng thành công');
        res.redirect('/admin/tinh-nang');
    }),

    deleteProduct: catchAsync(async (req, res) => {
        const { image } = await featureService.getOne(
            { _id: req.body._id },
            'image'
        );

        if (image) {
            fs.unlink(`public/${image}`, (err) => {
                if (err) {
                    req.flash('error', 'Xoá tính năng thất bại');
                    return res.redirect('back');
                }
            });
        }

        await featureService.delete({ _id: req.body._id }).catch((err) => {
            req.flash('error', 'Xoá tính năng thất bại');
            return res.redirect('back');
        });

        req.flash('success', 'Xoá tính năng thành công');
        res.redirect('back');
    }),
};

module.exports = userController;
