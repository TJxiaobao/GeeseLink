import request from "@/utils/request";

export function addSms(data) {
  return request({
    url: "/system/config/add/sms",
    method: "post",
    data,
  });
}

export function deleteSms(id) {
  return request({
    url: `/system/config/add/sms/${id}`,
    method: "delete",
  });
}

export function updateSms(data) {
  return request({
    url: "/system/config/update/sms",
    method: "post",
    data,
  });
}
export function listSms(query) {
  return request({
    url: "/system/config/list/sms",
    method: "get",
    params: query,
  });
}
export function getSms(id) {
  return request({
    url: "/system/config/getinfo/sms",
    method: "get",
    params: { id },
  });
}
