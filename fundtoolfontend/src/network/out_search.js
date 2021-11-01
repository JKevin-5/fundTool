import {get} from './out_axios'

//查询基金信息
export const getFund=(p) =>get('/fund?code='+p);

//查询股票信息
export const getStock=(p) => get('/stock?code='+p);

//查询基金历史信息
export const getFundHistory=(code,startDate) => get('/detail?code='+code+'&startDate='+startDate);