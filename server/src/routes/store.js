var express = require('express');
var router = express.Router({ mergeParams: true });

const upload = require('../utils/upload');

const { storeController } = require('../controllers');

// Path: /:userId/products
router.get('/', storeController.getUserStore);
router.post('/create', upload.single('product'), storeController.sellProduct);
router.post('/delete', storeController.deleteProduct);
router.post('/:productId', storeController.updateProduct);

module.exports = router;
