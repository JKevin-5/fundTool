import { get, post, deletes } from "./axios";

const BASE_URL = '/api'
// 查询持仓信息
export const getPossessions = (username) =>
  get(BASE_URL+"/possessions/getPossessions/" + username);

// 新增持仓信息
export const addPossession = (p) => post(BASE_URL+"/possessions/addPossession", p);

// 删除持仓信息
export const deletePossession = (p) => deletes(BASE_URL+""+p);
