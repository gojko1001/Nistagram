//const fs = require('fs')
module.exports = {
    "runtimeCompiler": true,
    chainWebpack: config => {
      config.module
        .rule('images')
          .use('url-loader')
            .loader('url-loader')
            .tap(options => Object.assign(options, { limit: 10240 }))
    },
  
    devServer: {
      proxy: {
        '^/api': {
          target: 'http://localhost:8762',
          ws: true,
         
          changeOrigin: true
        }
      },
      // https: {
      //   key: fs.readFileSync('src/certs/421623108.pem'),
      //   cert: fs.readFileSync('src/certs/421623108.cer'),
      // },
      public: 'http://localhost:3000/'
    },
    outputDir: '../../../target/front/public',

  }
  