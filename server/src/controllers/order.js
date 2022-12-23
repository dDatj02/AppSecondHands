const catchAsync = require('../utils/catchAsync');
const { marketService } = require('../services');

const orderController = {
    getSellingProducts: catchAsync(async (req, res) => {
        const list = await marketService
            .get(
                { status: 'censored' },
                { field: 'product seller -_id', populate: 'product seller' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail');
            });

        const result = list.map((item) => {
            return {
                ...item.product,
                sellStatus: '',
                sellerAvt: item.seller.avatar,
                sellerName: item.seller.name,
            };
        });
        res.status(200).json(result);
    }),

    getUserDoneOrders: catchAsync(async (req, res) => {
        const list = await marketService
            .get(
                { status: 'sold', buyer: req.params.userId },
                { field: 'product seller -_id', populate: 'product seller' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail');
            });

        const result = list.map((item) => {
            return {
                ...item.product,
                sellerAvt: item.seller.avatar,
                sellStatus: '',
                sellerName: item.seller.name,
            };
        });
        res.status(200).json(result);
    }),

    getUserBuyingOrders: catchAsync(async (req, res) => {
        const list = await marketService
            .get(
                { status: 'shipping', buyer: req.params.userId },
                { field: 'product seller -_id', populate: 'product seller' }
            )
            .catch((err) => {
                return res.status(400).send('Get data fail');
            });

        const result = list.map((item) => {
            return {
                ...item.product,
                sellerAvt: item.seller.avatar,
                sellStatus: '',
                sellerName: item.seller.name,
            };
        });
        res.status(200).json(result);
    }),
};

module.exports = orderController;
