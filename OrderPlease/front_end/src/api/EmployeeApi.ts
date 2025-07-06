import service from "@/utils/request";

export const login = (data: any) => {
    return service.post('/admin/employee/login', data)
}