var express = require('express');
var router = express.Router();

const { authController } = require('../controllers');

// Path: /auth
router.post('/login', authController.login);
router.post('/register', authController.register);
router.post('/change-password', authController.changePassword);

module.exports = router;
