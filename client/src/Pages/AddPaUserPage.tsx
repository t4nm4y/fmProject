import { Button, FlexBox, InputField, MenuList, MenuListItem } from 'fm-library';
import { Header } from '../Components/Header';
import { useState } from 'react';
import toast from 'react-hot-toast';
export function AddPaUser() {
	const [selectedBank, setSelectedBank] = useState<number | null>(null);
	const [mobileNo, setMobileNo] = useState<string | null>(null);
	const [creditLimit, setCreditLimit] = useState<number | null>(null);
	const [selectedTwoFA, setSelectedTwoFA] = useState<string | null>(null);

	const handleBankSelection = (lender: number) => {
		setSelectedBank(lender);
	};

	const handleTwoFASelection = (type: string) => {
		setSelectedTwoFA(type);
	};

	const handleAddPaUser = async () => {
		try {
			const backendURL = process.env.REACT_APP_BACKEND_URL;
			const response = await fetch(`${backendURL}/addPaUser`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({
					mobile: mobileNo,
					lender_id: selectedBank,
					credit_limit: creditLimit,
					two_fa_value: selectedTwoFA
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
			<FlexBox direction="column" flexStyle="center" gap="1.2em">
				<h1 className="heading">Add PA User Page</h1>
				<InputField inputVariant='base' placeholder={"Enter mobile no."} label="Mobile No."
					onChange={(e) => setMobileNo(e.target.value)}
				/>
				<InputField inputVariant='base' placeholder={"Enter credit limit"} label="Credit Limit"
					onChange={(e) => setCreditLimit(e.target.value)}
				/>
				<MenuList>
					<p className="subHeading">Select a bank:</p>
					<MenuListItem
						hasSubList={false}
						title='ICICI Bank'
						Icon={() => <img className="icon" src="https://iccdn.in/lenders/icici-icon-logo-v4.svg" alt="ICICI Bank" />}
						onClick={() => handleBankSelection(1)}
						active={selectedBank === 1}
					/>
					<MenuListItem
						hasSubList={false}
						title='Kotak Bank'
						Icon={() => <img className="icon" src="https://iccdn.in/img/kotak-icon-v1.png" alt="Kotak Bank" />}
						onClick={() => handleBankSelection(2)}
						active={selectedBank === 2}
					/>
					<MenuListItem
						hasSubList={false}
						title='HDFC Bank'
						Icon={() => <img className="icon" src="https://iccdn.in/img/hdfc-logo-icon-v2.png" alt="HDFC Bank" />}
						onClick={() => handleBankSelection(3)}
						active={selectedBank === 3}
					/>
					<MenuListItem
						hasSubList={false}
						title='CASHe'
						Icon={() => <img className="icon" src="https://iccdn.in/lenders/CASHe-icon.png" alt="CASHe" />}
						onClick={() => handleBankSelection(4)}
						active={selectedBank === 4}
					/>
					<MenuListItem
						hasSubList={false}
						title='KreditBee'
						Icon={() => <img className="icon" src="https://iccdn.in/lenders/kreditbee_square_logo.jpg" alt="KreditBee" /> }
						onClick={() => handleBankSelection(5)}
						active={selectedBank === 5}
					/>
				</MenuList>

				<MenuList>
					<p className="subHeading">Select 2FA type:</p>
					<MenuListItem
						hasSubList={false}
						title='DOB'
						onClick={() => handleTwoFASelection('DOB')}
						active={selectedTwoFA === 'DOB'}
					/>
					<MenuListItem
						hasSubList={false}
						title='PAN'
						onClick={() => handleTwoFASelection('PAN')}
						active={selectedTwoFA === 'PAN'}
					/>
				</MenuList>
				<Button onClick={handleAddPaUser}>Add PA User</Button>
				<br />
			</FlexBox>
		</>
	);
}