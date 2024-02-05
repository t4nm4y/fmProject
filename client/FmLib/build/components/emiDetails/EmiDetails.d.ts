import { FC } from 'react';
import { EmiPlans } from './EmiPlansTable';
interface EmiDetailsProps {
    lenderIcon: string;
    lenderName: string;
    offerText?: string;
    additionalInfo?: string;
    tableBottomText?: string;
    emiDetails: {
        data1: {
            text: string | React.ReactNode;
            value: string | React.ReactNode;
        };
        data2: {
            text: string | React.ReactNode;
            value: string | React.ReactNode;
        };
        data3: {
            text: string | React.ReactNode;
            value: string | React.ReactNode;
        };
        emiTableHeads: Array<string>;
        emiPlans: {
            tableRows: EmiPlans[][];
        };
    };
    onClick?: Function;
}
type Props = {
    emiDetailsList: Array<EmiDetailsProps>;
};
declare const EmiDetails: FC<Props>;
export { EmiDetails };
