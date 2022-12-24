var express = require('express');
var router = express.Router({ mergeParams: true });

const upload = require('../utils/upload');

const { storeController } = require('../controllers');

// Path: /:userId/products
router.get('/', storeController.getUserStore);
router.post('/create', upload.single('product'), storeController.sellProduct);
router.post('/done', storeController.doneOrder);
router.post('/delete', storeController.deleteProduct);

module.exports = router;
