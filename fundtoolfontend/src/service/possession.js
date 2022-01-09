import { get, post, deletes } from "./axios";

// 查询持仓信息
export const getPossessions = (username) =>
  get("/possessions/getPossessions/" + username);

// 新增持仓信息
export const addPossession = (p) => post("/possessions/addPossession", p);

// 删除持仓信息
export const deletePossession = (p) => deletes(""+p);
