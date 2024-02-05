import { Button, Collapse, FlexBox, MenuList, MenuListItem } from 'fm-library';
import React from 'react';

interface LenderDetail {
  lender_id: number;
  lender_name: string;
  icon_link: string;
  tenure: number;
  interest_rate: number;
  emi: number;
  total_amount: number;
}

interface LenderDetailsProps {
  tenures: LenderDetail[];
  onTenureClick: (tenure: number) => void;
  handleBack: (any);
}

const LenderDetails: React.FC<LenderDetailsProps> = ({ tenures, onTenureClick, handleBack }) => {
  return (
    <FlexBox direction="column" flexStyle="center" gap="1.2em">
      <FlexBox className="subHeading">
      <button className="backBtn" onClick={handleBack}> {`<`} </button>
      <img src={tenures[0].icon_link} alt="bank icon" className="icon" />
        {tenures[0].lender_name}
      </FlexBox>
      <MenuList>
        <p className="subHeading">Choose a Tenure:</p>
        {tenures.map((tenure) => (
          <MenuListItem
            hasSubList={false}
            title={`${tenure.tenure} Months, ${tenure.emi}/month, ₹${tenure.total_amount} @${tenure.interest_rate}% p.a.`}
            key={tenure.tenure}
            onClick={() => onTenureClick(tenure.tenure)}
          />
        ))}
      </MenuList>
      <Button >Confirm</Button>
    </FlexBox>
  );
};


export default LenderDetails;
