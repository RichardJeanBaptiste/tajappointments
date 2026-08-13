import { useState } from 'react'
import { Button, Form,  } from 'react-bootstrap';
import './App.css'
import * as React from "react";

function App() {
  
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

      const message = await response.text();

      console.log(message);
  }

  const handleRegistrationSubmit = async (e: any) => {

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

      const message = await response.text();

      console.log(message);
  }

  return (
    <div>
        <Form style={{ width: '45%' }}>
          <Form.Group className='mb-3' controlId='loginEmail'>
              <Form.Label>Email: </Form.Label>
              <Form.Control type="email" placeholder='email' onChange={(e) => handleLoginFormChange(e,"email")} value={loginForm.email}/>
          </Form.Group>

          <Form.Group>
            <Form.Label>Password: </Form.Label>
            <Form.Control type='password' placeholder='password' onChange={(e) => handleLoginFormChange(e, "password")} value={loginForm.password}/>
          </Form.Group>

          <Button variant='primary' type="submit" onClick={handleLoginSubmit}>Submit</Button>
        </Form>

        <Form style={{ width: '45%', marginTop: '5%' }}>
            <Form.Group>
                <Form.Label>Email: </Form.Label>
                <Form.Control type="email" placeholder="email" onChange={(e) => handleRegistrationFormChange(e,"email")} value={registrationForm.email}/>
            </Form.Group>

            <Form.Group>
                <Form.Label>Password: </Form.Label>
                <Form.Control type="password" placeholder="password" onChange={(e) => handleRegistrationFormChange(e, "password")} value={registrationForm.password}/>
            </Form.Group>

            <Button variant="primary" onClick={handleRegistrationSubmit}>Submit</Button>
        </Form>
    </div>
  )
}

export default App
