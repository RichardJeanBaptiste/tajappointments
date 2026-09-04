import { useState } from 'react'
import { Button, Form, FloatingLabel, Stack, Fade } from 'react-bootstrap';
import { useNavigate } from 'react-router';
import './App.css'
import * as React from "react";
import { useAuth } from './components/AuthContext';

function App() {

    
    const HomePage = () => {

        let navigate = useNavigate();
        const { login } = useAuth();

        const [showLogin, setShowLogin] = useState(true);
    
        const [ loginForm, setLoginForm ] = useState({
            email: '',
            password: ''
        });

        const [ registrationForm, setRegistrationForm ] = useState({
            email: '',
            password: ''
        });


        const handleLoginFormChange = (e: React.ChangeEvent<any>, name: string) => {

            const value = e.target.value;

            setLoginForm((prev) => ({
                ...prev,
                [name]: value
            }));
        }

        const handleRegistrationFormChange = (e: React.ChangeEvent<any>, name: string)=> {

            const value = e.target.value;

                setRegistrationForm((prev) => ({
                    ...prev,
                    [name]: value
                }));
        }

        const handleLoginSubmit = async (e: any) => {
            //console.log(loginForm);
            
            e.preventDefault();

            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    loginEmail: loginForm.email,
                    loginPassword: loginForm.password
                })
            });

            //const message = await response.text();

            //console.log(message);
            const status = await response.status;

            if(status == 200) {
                const data = await response.json();
                login(data.token);
                navigate("/dashboard");
            }

            if(status == 400) {
            // console.log(message);
                const message = await response.text();
                alert(message);
            }

            if(status == 403) {
                console.log("Something went wrong")
            }


        }

        const handleRegistrationSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {

            e.preventDefault();

            const response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userEmail: registrationForm.email,
                    userPassword: registrationForm.password
                })
            });

            
            const status = await response.status;

            console.log(status);

            if(status == 201) {
                
                const data = await response.json();
                console.log(data);
                login(data.token);
                navigate("/dashboard");
            }

            if(status == 400) {
            // console.log(message);
                const message = await response.text();
                alert(message);
            }

            if(status == 403) {
                console.log("Something went wrong")
            }

        }

        const Login = () => {
            return (
                <>
                    <Fade in={showLogin} appear unmountOnExit>
                        <div className='formStyle'>
                            <h2>Welcome Back</h2>

                            <Form.Group className='mb-3'>
                                <FloatingLabel controlId='loginEmail' label="Email">
                                    <Form.Control type='email' placeholder='Email' onChange={(e) => handleLoginFormChange(e, "email")} value={loginForm.email}/>
                                </FloatingLabel>
                            </Form.Group>

                            <Form.Group className='mb-3'>
                                <FloatingLabel controlId='loginPassword' label="Password">
                                    <Form.Control type='password' placeholder='password' onChange={(e) => handleLoginFormChange(e, "password")} value={loginForm.password}/>
                                </FloatingLabel>
                            </Form.Group>

                            <Stack gap={2}>
                                <Button variant='primary' type="submit" onClick={handleLoginSubmit}>Submit</Button>
                                <Button variant='outline-secondary' onClick={() => setShowLogin(!showLogin)}>Create An Account</Button>
                            </Stack>
                        </div>
                    </Fade>
                    
                </>
            )
        }

        const Registration = () => {
            return (
                <>  
                    <Fade in={!showLogin} appear unmountOnExit>
                        <div className='formStyle'>
                            <h2>Registration Form</h2>
                            <Form.Group className='mb-3'>
                                <Form.Label>Email: </Form.Label>
                                <Form.Control type="email" placeholder="email" onChange={(e) => handleRegistrationFormChange(e,"email")} value={registrationForm.email}/>
                            </Form.Group>

                            <Form.Group className='mb-3'>
                                <Form.Label>Password: </Form.Label>
                                <Form.Control type="password" placeholder="password" onChange={(e) => handleRegistrationFormChange(e, "password")} value={registrationForm.password}/>
                            </Form.Group>

                            <Form.Group className='mb-3'>
                                <Form.Label>Password: </Form.Label>
                                <Form.Control type="password" placeholder="password" onChange={(e) => handleRegistrationFormChange(e, "password")} value={registrationForm.password}/>
                            </Form.Group>

                            <Stack gap={2}>
                                <Button variant="primary" onClick={handleRegistrationSubmit}>Submit</Button>
                                <Button variant='outline-secondary' onClick={() => setShowLogin(!showLogin)}> Have An Account ?</Button>
                            </Stack>
   
                        </div>
                    </Fade>
                </>
            )
        }

    return (
        <div className='lRoot'>
            {(showLogin) ? <Login/> : <Registration/> }
        </div>
    )
  }

  return (
    <HomePage/>
  )
}

export default App
