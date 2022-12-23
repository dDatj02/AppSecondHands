const catchAsync = require('../utils/catchAsync');
const { productService, userService } = require('../services');

const orderController = {
    getSellingProducts: catchAsync(async (req, res) => {
        const users = await userService
            .get({}, { field: 'store name', populate: 'store.product' })
            .catch((err) => {
                return res.status(400).send('Get data fail' + err);
            });
        const result = users.map((user) => {
            if (user.store.status == 'censored') {
                return ;
            }
        });
        res.status(200).json(users);
    }),
};

module.exports = orderController;
