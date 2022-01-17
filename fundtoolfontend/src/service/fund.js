import {get,post} from './axios'

const BASE_URL = '/api'
const FUND_URL = '/fund'

//模糊搜索功能 单个基金信息
export const getFunds=(p) =>get(BASE_URL+'/funds/findFunds/'+p);

//模糊搜索功能 单个基金信息
export const getFundHis=(p) =>get(BASE_URL+'/funds/findFundHis/'+p);

//基金信息list
export const getFundList = (p) => get(BASE_URL+'/funds/findFundList/'+p);

//更新基金历史数据
export const updateFundHis=(p) => post(BASE_URL+'/funds/updateFundHis',p);


// 外部链接
// 获取实时基金列表
export const getFundListNow = (p) => get(FUND_URL+'/'+p+'.js?');