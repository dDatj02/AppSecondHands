const catchAsync = require('../utils/catchAsync');
const { productService, marketService } = require('../services');

const adminController = {
    getPendingProducts: catchAsync(async (req, res) => {
        const list = await marketService
            .get(
                { status: 'pending' },
                { field: 'seller product status', populate: 'seller product' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail' + err);
            });
        const result = list.map((item) => {
            return {
                ...item.product,
                sellerAvt: item.seller.avatar,
                sellStatus: item.status,
                sellerName: item.seller.name,
            };
        });
        res.status(200).json(result);
    }),

    censorProduct: catchAsync(async (req, res) => {
        const { productId } = req.body;

        await marketService
            .update({ product: productId }, { status: 'censored' })
            .catch((err) => {
                return res.status(400).send('Censor product fail');
            });
        res.status(200).send();
    }),
};

module.exports = adminController;
