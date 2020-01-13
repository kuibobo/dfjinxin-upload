require.config({
  shim: {
    'iview': ['vue', 'axios', 'cascaderMulti']
  },
  paths: {
    'iview': 'app/plugins/iview/js/iview',
    'cascaderMulti': 'app/plugins/iview/js/cascader-multi',
  }
});