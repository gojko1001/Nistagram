module.exports = {
    "runtimeCompiler": true,
  
    devServer: {
      proxy: {
        '^/api': {
          target: 'http://localhost:3030',
          ws: true,
         
          changeOrigin: true
        }
      }
    },
    outputDir: '../../../target/front/public',
  }
  