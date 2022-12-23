const { marketModel } = require('../models');

const marketService = {
    getOne: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await marketModel.findOne(query, field).populate(populate).lean();
    },

    get: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await marketModel.find(query, field).populate(populate).lean();
    },

    create: async (payloads) => {
        return await marketModel.create(payloads);
    },

    update: async (conditions, payloads) => {
        return await marketModel.findOneAndUpdate(conditions, payloads);
    },

    delete: async (conditions) => {
        return await marketModel.findOneAndDelete(conditions);
    },
};

module.exports = marketService;
