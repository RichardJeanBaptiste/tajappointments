import { Navigate, Outlet, useLocation } from 'react-router';
import { useAuth } from "./AuthContext";

export function ProtectedRoute() {
    const { token } = useAuth();
    const location = useLocation();

    if(!token) {
        return <Navigate to="/" state={{ from: location }} replace/>
    }

    return <Outlet/>
}