const fs = require('fs')
module.exports = {
    "runtimeCompiler": true,
  
    devServer: {
      proxy: {
        '^/api': {
          target: 'https://localhost:3030',
          ws: true,
         
          changeOrigin: true
        }
      },
      https: {
        key: fs.readFileSync('src/certs/421623108.pem'),
        cert: fs.readFileSync('src/certs/421623108.cer'),
      },
      public: 'https://localhost:3000/'
    },
    outputDir: '../../../target/front/public',

  }
  