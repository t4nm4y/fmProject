import { FC } from 'react';
import { ColorTokenKeys } from '../../types';
interface DividerProps {
    thickness?: number;
    color?: ColorTokenKeys;
    width?: number;
    height?: number;
    orientation?: 'horizontal' | 'vertical';
    fullWidth?: boolean;
}
declare const Divider: FC<DividerProps>;
export { Divider };
