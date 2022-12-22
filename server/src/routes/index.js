var express = require('express');
var router = express.Router();

var userRouter = require('./user');
var adminRouter = require('./admin');
var authRouter = require('./auth');

const { productController } = require('../controllers');

const classifyUser = require('../utils/classifyUser');

router.use(classifyUser);
router.use('/admin', adminRouter);
router.use('/auth', authRouter);
router.get('/selling-products', productController.getSellingProducts);
router.use('/', userRouter);

module.exports = router;
