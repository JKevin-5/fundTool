var storage = window.localStorage;
//读取localstorage的token
export const getTokenLocalstorage = () =>{
    return storage.token;
}
//写入localstorage信息
export const setTokenLocalstorage = (token) =>{
    storage.token = token;
}