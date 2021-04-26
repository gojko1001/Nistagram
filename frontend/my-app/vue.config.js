module.exports = {
    "runtimeCompiler": true,
  
    devServer: {
      proxy: {
        '^/api': {
          target: 'https://localhost:3030',
          ws: true,
         
          changeOrigin: true
        }
      }
    },
    outputDir: '../../../target/front/public',
  }
  