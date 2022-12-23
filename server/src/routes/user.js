var express = require('express');
var router = express.Router();

const storeRouter = require('./store');
const { userController, orderController } = require('../controllers');
const upload = require('../utils/upload');

// Path: /
router.post('/:userId', upload.single('user'), userController.updateProfile);
router.use('/:userId/products', storeRouter);
router.use('/:userId/done-orders', orderController.getUserDoneOrders);
router.use('/:userId/buying-orders', orderController.getUserBuyingOrders);

module.exports = router;
