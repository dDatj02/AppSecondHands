const catchAsync = require('../utils/catchAsync');
const { userService } = require('../services');

const userController = {
    updateProfile: catchAsync(async (req, res) => {
        let { avatar } = await userService.getOne(
            { _id: req.params.userId },
            { field: 'avatar' }
        );
        avatar = req.file ? '/uploads/user/' + req.file.filename : avatar;

        const user = await userService
            .update({ _id: req.params.userId }, { ...req.body, avatar })
            .catch((err) => {
                return res.status(400).send('Update data fail');
            });
        res.status(200).json(user);
    }),
};

module.exports = userController;
