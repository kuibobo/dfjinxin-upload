require.config({
    shim: {
        'daterangepicker': ['moment', 'jquery'],
    },
    paths: {
        'daterangepicker': 'app/plugins/daterangepicker/daterangepicker',
        'moment': 'app/plugins/daterangepicker/moment.min',
    }
});