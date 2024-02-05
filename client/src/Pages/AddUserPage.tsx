
import { Button, FlexBox, InputField } from 'fm-library';
import { Header } from '../Components/Header';
import { useState } from 'react';
import toast from 'react-hot-toast';
export function AddUser() {
	const [mobileNo, setMobileNo] = useState('');
	const [firstName, setFirstName] = useState('');
	const [lastName, setLastName] = useState('');

	const handleAddUser = async () => {
		try {
			const backendURL = process.env.REACT_APP_BACKEND_URL;
			const response = await fetch(`${backendURL}/addUser`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				  },
				  body: JSON.stringify({
					mobile: mobileNo,
					first_name: firstName,
					last_name: lastName,
				  }),
			});

			if (response.ok) {
				const result = await response.text();
				toast(result);
			} else {
				const errorResult = await response.text();
				toast.error(errorResult);
				// console.error(`Failed to add user. Error: ${errorResult}`);
			}
		} catch (error) {
			console.error('Error:', error);
		}
	};

	return (
		<>
			<Header />
			<FlexBox direction="column" flexStyle="center" gap="1.1em">
				<h1 className="heading">Add User Page</h1>
				<InputField inputVariant={'base'} placeholder={"Enter Mobile No."} label={"Mobile"}
					onChange={(e) => setMobileNo(e.target.value)}
				/>
				<InputField inputVariant={'base'} placeholder={"Enter first name"} label="First Name"
					onChange={(e) => setFirstName(e.target.value)}
				/>
				<InputField inputVariant={'base'} placeholder={"Enter last name"} label="Last Name"
					onChange={(e) => setLastName(e.target.value)}
				/>
				<Button onClick={handleAddUser}>Add User</Button>
			</FlexBox>
		</>
	);
}
