import {get,post} from './axios'

//模糊搜索功能 单个基金信息
export const getFunds=(p) =>get('/funds/findFunds/'+p);

//模糊搜索功能 单个基金信息
export const getFundHis=(p) =>get('/funds/findFundHis/'+p);

//基金信息list
export const getFundList = (p) => get('/funds/findFundList/'+p);

//更新基金历史数据
export const updateFundHis=(p) => post('/funds/updateFundHis',p);