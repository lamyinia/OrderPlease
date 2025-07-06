import axios from "axios"

const service = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    "timeout": 600000
})

export default service