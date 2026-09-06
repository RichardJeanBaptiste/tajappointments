import { jwtDecode } from "jwt-decode";
import { createContext, useContext, useState, type ReactNode } from "react";

interface AuthContextType {
    token: string | null;
    login: (token: string) => void;
    logout: () => void;
}

interface JwtPayload {
    sub : string;
    exp : number; 
}

function isTokenExpired(token: string) : boolean {
    try {
        const decoded = jwtDecode<JwtPayload>(token);
        return decoded.exp * 1000 < Date.now();
    } catch (error) {
        return true;
    }
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {

    const [ token, setToken ] = useState<string | null>(() => {
        const stored = localStorage.getItem("token");
        if(stored && !isTokenExpired(stored)) return stored;

        localStorage.removeItem("token");
        return null;
    });

    const login = (newToken: string) => {
        localStorage.setItem("token", newToken);
        setToken(newToken);
    }

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
    }

    return (
        <AuthContext.Provider value={{ token, login, logout }} >
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth() {
    const ctx = useContext(AuthContext);
    if (!ctx) throw new Error("useAuth must be used within AuthProvider");
    return ctx;
}