import { FC } from 'react';
import { ColorTokenKeys } from '../../types';
type ValueType = 'tenure' | 'currency' | 'generic';
export interface EmiPlans {
    value: string | number | React.ReactNode;
    type: ValueType;
    offerText?: string;
}
interface EmiPlansTableProps {
    tableHeads: Array<string>;
    tableRows: EmiPlans[][];
    borderColor?: ColorTokenKeys;
    tableBottomText?: string;
}
declare const EmiPlansTable: FC<EmiPlansTableProps>;
export { EmiPlansTable };
