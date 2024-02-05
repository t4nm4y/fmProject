
import { Button, FlexBox, InputField } from 'fm-library';
import { Header } from '../Components/Header';
import { useState } from 'react';
import toast from 'react-hot-toast';

export function DeleteUser() {
  const [mobileNo, setMobileNo] = useState(null);

  const handleDeleteUser = async () => {
    try {
      const backendURL = process.env.REACT_APP_BACKEND_URL;

      const response = await fetch(`${backendURL}/delete/${mobileNo}`, {
        method: 'DELETE',
      });

      if (response.ok) {
		const result = await response.text();
		toast(result);
      } else {
		const result = await response.text();
        toast.error(result);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <>
      <Header />
      <FlexBox direction="column" flexStyle="center" gap="1.1em">
        <h1 className="heading">Delete User Page</h1>
          <InputField
            inputVariant={'base'}
            placeholder={'Enter Mobile No.'}
            label={'Mobile'}
            value={mobileNo}
            onChange={(e) => setMobileNo(e.target.value)}
          />
          <Button onClick={handleDeleteUser}>Delete User</Button>
      </FlexBox>
    </>
  );
}
