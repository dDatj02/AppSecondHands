var express = require('express');
var router = express.Router();

const storeRouter = require('./store');
const { userController, orderController } = require('../controllers');
const upload = require('../utils/upload');

// Path: /
router.post('/:userId', upload.single('user'), userController.updateProfile);
router.use('/:userId/products', storeRouter);
router.get('/:userId/done-orders', orderController.getUserDoneOrders);
router.get('/:userId/buying-orders', orderController.getUserBuyingOrders);
router.post('/:userId/buy', orderController.buyProduct);

module.exports = router;
