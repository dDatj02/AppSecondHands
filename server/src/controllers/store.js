const catchAsync = require('../utils/catchAsync');
const { productService, marketService } = require('../services');

const storeController = {
    getUserStore: catchAsync(async (req, res) => {
        const store = await marketService
            .get(
                { seller: req.params.userId },
                { field: 'seller product status', populate: 'seller product' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail' + err);
            });
        const result = store.map((item) => {
            return {
                ...item.product,
                sellerAvt: item.seller.avatar,
                sellStatus: item.status,
                sellerName: item.seller.name,
            };
        });
        res.status(200).json(result);
    }),

    sellProduct: catchAsync(async (req, res) => {
        const image = req.file ? '/uploads/product/' + req.file.filename : '';

        const product = await productService
            .create({ ...req.body, image })
            .catch((err) => {
                console.log(err);
                return res.status(400).send('Create product fail ' + err);
            });

        await marketService
            .create({
                product: product._id,
                seller: req.params.userId,
                status: 'pending',
            })
            .catch((err) => {
                return res.status(400).send('Add product to user fail' + err);
            });
        res.status(200).send();
    }),

    updateProduct: catchAsync(async (req, res) => {}),

    deleteProduct: catchAsync(async (req, res) => {}),
};

module.exports = storeController;
