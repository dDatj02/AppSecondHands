const jwt = require('jsonwebtoken');
const catchAsync = require('../utils/catchAsync');

module.exports = catchAsync(async (req, res, next) => {
    const PATH_FOR_ANONYMOUS = ['/auth', '/'];

    const isPathForAnonymous = PATH_FOR_ANONYMOUS.some((endpoint) =>
        req.originalUrl.includes(endpoint)
    );

    if (isPathForAnonymous) return next();

    const token = req.get('AuthToken');
    if (!token) {
        return res.status(403).send('Empty token');
    }

    jwt.verify(token, 'sud', async (err) => {
        if (err) {
            return res.status(403).send('Invalid token');
        }
        return next();
    });
});
