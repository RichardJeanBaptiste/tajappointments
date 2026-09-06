import { useAuth } from "./AuthContext";

export function useApi() {
    const { token, logout } = useAuth();

    const apiFetch = async (url: string, options: RequestInit = {}) => {
        const response = await fetch(url, {
            ...options,
            headers: {
                ...options.headers,
                Authorization: `Bearer ${token}`
            }
        });

        if (response.status === 401) {
            logout();
            window.location.href = "/login"
        }

        return response;
    }

    return { apiFetch };
}