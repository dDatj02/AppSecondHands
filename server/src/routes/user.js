var express = require('express');
var router = express.Router();

const upload = require('../utils/upload');

const { userController } = require('../controllers');

router.get('/:userId', userController.getProfile);
router.get('/:userId/products', userController.getProducts);
router.post(
    '/:userId/products/create',
    upload.single('product'),
    userController.createProduct
);
router.get('/:userId/products/:productId', userController.getProductDetail);
router.post('/:userId/products/delete', userController.deleteProduct);
router.post('/:userId/products/:productId', userController.updateProduct);

module.exports = router;
