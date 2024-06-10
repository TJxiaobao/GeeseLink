import request from "@/utils/request";

export function addEmail(data) {
  return request({
    url: "/system/config/add/email",
    method: "post",
    data,
  });
}

export function deleteEmail(id) {
  return request({
    url: "/system/config/del/email",
    method: "delete",
    params : {
      id: id,
    }
  });
}

export function updateEmail(data) {
  return request({
    url: "/system/config/update/email",
    method: "post",
    data,
  });
}
export function listEmail(query) {
  return request({
    url: "/system/config/list/email",
    method: "get",
    params: query,
  });
}
export function getEmail(id) {
  return request({
    url: "/system/config/get_info/email",
    method: "get",
    params: { id },
  });
}
