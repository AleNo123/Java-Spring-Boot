const { createProxyMiddleware } = require('http-proxy-middleware');


module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http:/192.168.1.71:8080',
      changeOrigin: true,
      pathRewrite: { '^/api': '' },
    })
  );
};