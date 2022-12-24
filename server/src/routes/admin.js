var express = require('express');
var router = express.Router();

const { adminController } = require('../controllers');

// Path: /admin
router.get('/pending-products', adminController.getPendingProducts);

router.post('/censor', adminController.censorProduct);

module.exports = router;
