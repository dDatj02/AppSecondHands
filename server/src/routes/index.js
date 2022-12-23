var express = require('express');
var router = express.Router();

var userRouter = require('./user');
var adminRouter = require('./admin');
var authRouter = require('./auth');

const { orderController } = require('../controllers');

router.use('/admin', adminRouter);
router.use('/auth', authRouter);
router.get('/selling-products', orderController.getSellingProducts);
router.use('/', userRouter);

module.exports = router;
