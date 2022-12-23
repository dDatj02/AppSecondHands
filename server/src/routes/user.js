var express = require('express');
var router = express.Router();

const storeRouter = require('./store');
const { userController } = require('../controllers');
const upload = require('../utils/upload');

// Path: /
router.post('/:userId', upload.single('user'), userController.updateProfile);
router.use('/:userId/products', storeRouter);

module.exports = router;
