import { FC } from 'react';
import { BoxProps as MuiBoxProps } from '@mui/material';
import { ColorTokenKeys } from '../../types';
type BoxProps = {
    color?: ColorTokenKeys;
    bgcolor?: ColorTokenKeys;
} & MuiBoxProps;
export declare const Box: FC<BoxProps>;
export { BoxProps };
