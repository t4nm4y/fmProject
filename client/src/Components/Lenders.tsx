import { FlexBox, MenuList, MenuListItem } from 'fm-library';
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

interface TenureData {
    [tenure: number]: LenderDetail[];
}

interface LendersData {
    amount: number;
    mobile: string;
    tenures: TenureData;
}

interface LendersProps {
    lenders: LendersData;
    onLenderClick: (tenure:LenderDetail[]) => void;
}

const Lenders: React.FC<LendersProps> = ({ lenders, onLenderClick }) => {
    return (
        <FlexBox direction="column" flexStyle="center" gap="1.2em">
            <MenuList>
            <p className="subHeading">Choose a Lender:</p>
        {Object.keys(lenders.tenures).map((tenure) => (
          <MenuListItem
            hasSubList={false}
            title={lenders?.tenures[parseInt(tenure, 10)][0].lender_name || ''}
            key={tenure}
            Icon={() => <img className="icon" src={lenders?.tenures[parseInt(tenure, 10)][0].icon_link || ''} alt="Lender Icon" />}
            onClick={() => onLenderClick(lenders?.tenures[parseInt(tenure, 10)])}
          />
        ))}
      </MenuList>
        </FlexBox>
    );
};

export default Lenders;
