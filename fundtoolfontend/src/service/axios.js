import axios from 'axios';
//创建axios实例
const instance = axios.create({
    timeout: 10000   //请求超时时间
})

//get请求
export const get = (url, params, config = {}) => {
    return new Promise((resolve, reject) => {
      instance({
        method: 'get',
        url,
        params,
        headers: {
          'token': window.localStorage.token
        },
        ...config
      },).then(response => {
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  }

//post请求
export const post = (url, data, config = {}) => {
    return new Promise((resolve, reject) => {
      instance({
        method: 'post',
        url,
        data,
        headers: {
          'token': window.localStorage.token
        },
        ...config
      }).then(response => {
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  }
  
//delete请求
export const deletes = (url, data, config = {}) => {
    return new Promise((resolve, reject) => {
      instance({
        method: 'delete',
        url,
        data,
        ...config
      }).then(response => {
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  }

//put请求
export const put = (url, data, config = {}) => {
  return new Promise((resolve, reject) => {
    instance({
      method: 'put',
      url,
      data,
      ...config
    }).then(response => {
      resolve(response)
    }).catch(error => {
      reject(error)
    })
  })
}