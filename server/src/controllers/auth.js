const catchAsync = require('../utils/catchAsync');
const { userService } = require('../services');
const bcrypt = require('bcryptjs');

const authController = {
    login: catchAsync(async (req, res) => {
        const { email, password } = req.body;
        if (!email || !password) {
            return res.status(400).send('Empty data');
        }

        const user = await userService.getOne({ email }, {}).catch((err) => {
            return res.status(400).send(err);
        });

        if (user && bcrypt.compareSync(password, user.password))
            return res.status(200).json(user);
        else return res.status(400).send('Wrong data');
    }),

    register: catchAsync(async (req, res) => {
        await userService.create({ ...req.body }).catch((err) => {
            return res.status(400).send('Register fail' + err);
        });
        res.status(200).send();
    }),

    changePassword: catchAsync(async (req, res) => {
        const { _id, oldPassword, newPassword } = req.body;

        if (!_id || !oldPassword || !newPassword)
            return res.status(400).send('Empty data');

        const { password } = await userService.getOne(
            { _id },
            { field: 'password' }
        );

        if (!bcrypt.compareSync(oldPassword, password))
            return res.status(400).send('Incorrect password');

        await userService
            .update({ _id }, { password: await bcrypt.hash(newPassword, 12) })
            .catch((err) => {
                return res.status(400).send('Change password fail');
            });

        res.status(200).send();
    }),
};

module.exports = authController;
