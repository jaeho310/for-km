const path = require("path");
module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    overlay: false,
    port: 8086
  },
  lintOnSave: false,
  configureWebpack: {
    devtool: 'source-map'
  },
   outputDir: path.resolve(__dirname, "../src/main/resources/static"),
   indexPath: path.resolve(__dirname, "../src/main/resources/static/index.html"),
}
