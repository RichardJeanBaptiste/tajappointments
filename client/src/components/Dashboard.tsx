import { Button } from "react-bootstrap";
import { useAuth } from "./AuthContext";
//import { useEffect } from "react";

export default function Dashboard() {

    const { token , logout } = useAuth();

    if(token == null) {
        return (
            <div>
                <h1>Logged Out</h1>
            </div>
        )
    } else {
        return (
            <div>

                <h1>User Dashboard</h1>
                <Button variant="primary" onClick={() => console.log(localStorage.getItem("token"))}>Check Token</Button>
                <Button variant="danger" onClick={logout}>Logout</Button>
            </div>
        )
    }

    
}