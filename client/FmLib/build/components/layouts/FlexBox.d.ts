import { FC } from 'react';
import { BoxProps } from './Box';
type FlexStyles = 'center' | 'start' | 'end' | 'start-end' | 'end-start' | 'center-start' | 'start-center' | 'end-center' | 'center-end' | 'sb-center' | 'sb-start' | 'sb-end' | 'se-center' | 'se-start' | 'se-end' | 'sa-center' | 'sa-start' | 'sa-end';
type direction = 'row' | 'column';
export type FlexBoxProps = {
    direction?: direction;
    flexStyle?: FlexStyles;
} & BoxProps;
declare const FlexBox: FC<FlexBoxProps>;
export { FlexBox };
