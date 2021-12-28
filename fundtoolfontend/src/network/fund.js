import {get} from './axios'

//模糊搜索功能 单个基金信息
export const getFunds=(p) =>get('/funds/findFunds/'+p);

//模糊搜索功能 单个基金信息
export const getFundHis=(p) =>get('/funds/findFundHis/'+p);