// // vue.config.js
// // 解决同源跨域问题
// module.exports = {
//   devServer: {
//     host: 'localhost',
//     port: 80,
//       proxy: {
//           '/api': {
//               target: 'http://localhost:8001',// 你要请求的后端接口ip+port
//               changeOrigin: true,// 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
//               ws: true,// 开启webSocket
//               pathRewrite: {
//                   '^/api': '',// 替换成target中的地址
//               }
//           },
//         //   '/xinlang':{
//         //       target: 'http://hq.sinajs.cn',// 你要请求的后端接口ip+port
//         //       changeOrigin: true,// 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
//         //       ws: true,// 开启webSocket
//         //       pathRewrite: {
//         //           '^/xinlang': '',// 替换成target中的地址
//         //       }
//         //   },
//           '/tiantian':{
//             target: 'https://api.doctorxiong.club/v1/fund',// 你要请求的后端接口ip+port
//             changeOrigin: true,// 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
//             ws: true,// 开启webSocket
//             pathRewrite: {
//                 '^/tiantian': '',// 替换成target中的地址
//             }
//         }
//       }
//   }
// }
