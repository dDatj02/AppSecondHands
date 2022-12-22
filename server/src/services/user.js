const { userModel } = require('../models');

const userService = {
    getOne: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await userModel.findOne(query, field).populate(populate).lean();
    },

    get: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await userModel.find(query, field).populate(populate).lean();
    },

    create: async (payloads) => {
        return await userModel.create(payloads);
    },

    update: async (conditions, payloads) => {
        return await userModel.findOneAndUpdate(conditions, payloads);
    },

    delete: async (conditions) => {
        return await userModel.findOneAndDelete(conditions);
    },
};

module.exports = userService;
